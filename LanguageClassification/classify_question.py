from LanguageClassification.LangClassifier import LangClassifier
from DataBaseConnection import DataBaseConnection
from LanguageClassification import bayesian


def connect_db():
    database_connection = DataBaseConnection.DataBaseConnection()
    db = DataBaseConnection.DataBaseConnection.connect_database(database_connection)
    return db, database_connection


def question_snippet(db, connection):
    question_df = DataBaseConnection.DataBaseConnection.question_snippet(connection, db)
    return question_df


def save_classification(connection, db, question_df):
    DataBaseConnection.DataBaseConnection.save_question_classification(connection, db, question_df)


def main():
    db, connection = connect_db()
    question_df = question_snippet(db, connection)
    classifier1 = LangClassifier()
    classifier2 = LangClassifier()

    question_df = question_snippet(db, connection)
    java_df = bayesian.train_java(classifier1, question_df)
    question_df = question_snippet(db, connection)
    kotlin_df = bayesian.train_kotlin(classifier2, question_df)

    language_df = bayesian.create_dataframe(java_df, kotlin_df)

    save_classification(connection, db, language_df)


if __name__ == "__main__":
    main()
