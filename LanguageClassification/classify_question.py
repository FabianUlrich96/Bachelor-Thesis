from LanguageClassification.LangClassifier import LangClassifier
from DataBaseConnection import DataBaseConnection
from LanguageClassification import bayesian
import pandas as pd


def connect_db():
    database_connection = DataBaseConnection.DataBaseConnection()
    db = DataBaseConnection.DataBaseConnection.connect_database(database_connection)
    return db, database_connection


def question_snippet(db, connection):
    question_df = DataBaseConnection.DataBaseConnection.question_snippet(connection, db)
    return question_df


def create_dataframe(xml_df, java_df, kotlin_df):
    temp_df = pd.DataFrame(columns=['id', 'code_snippet', 'language', 'propability'])
    temp_df['id'] = xml_df['id'].values
    temp_df['code_snippet'] = xml_df['code_snippet'].values
    temp_df['xml'] = xml_df['propability'].values
    temp_df['java'] = java_df['propability'].values
    temp_df['kotlin'] = kotlin_df['propability'].values

    language_df = pd.DataFrame(columns=['id', 'code_snippet', 'language'])

    language_df['language'] = temp_df[["xml", "java", "kotlin"]].idxmax(axis=1)
    language_df['id'] = temp_df['id']
    language_df['code_snippet'] = temp_df['code_snippet']
    language_df['propability'] = temp_df[["xml", "java", "kotlin"]].max(axis=1)

    return language_df


def save_classification(connection, db, question_df):
    DataBaseConnection.DataBaseConnection.save_question_classification(connection, db, question_df)


def main():
    db, connection = connect_db()
    question_df = question_snippet(db, connection)
    classifier1 = LangClassifier()
    classifier2 = LangClassifier()
    classifier3 = LangClassifier()
    xml_df = bayesian.train_xml(classifier1, question_df)
    question_df = question_snippet(db, connection)
    java_df = bayesian.train_java(classifier2, question_df)
    question_df = question_snippet(db, connection)
    kotlin_df = bayesian.train_kotlin(classifier3, question_df)

    language_df = create_dataframe(xml_df, java_df, kotlin_df)

    save_classification(connection, db, language_df)


if __name__ == "__main__":
    main()
