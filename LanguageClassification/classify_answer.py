from LanguageClassification.LangClassifier import LangClassifier
from DataBaseConnection import DataBaseConnection
from LanguageClassification import bayesian


def connect_db():
    database_connection = DataBaseConnection.DataBaseConnection()
    db = DataBaseConnection.DataBaseConnection.connect_database(database_connection)
    return db, database_connection


def answer_snippet(db, connection):
    answer_df = DataBaseConnection.DataBaseConnection.answer_snippet(connection, db)
    return answer_df


def save_classification(connection, db, answer_df):
    DataBaseConnection.DataBaseConnection.save_answer_classification(connection, db, answer_df)


def main():
    db, connection = connect_db()
    answer_df = answer_snippet(db, connection)
    classifier1 = LangClassifier()
    classifier2 = LangClassifier()
    answer_df = answer_snippet(db, connection)
    java_df = bayesian.train_java(classifier1, answer_df)
    answer_df = answer_snippet(db, connection)
    kotlin_df = bayesian.train_kotlin(classifier2, answer_df)

    language_df = bayesian.create_dataframe(java_df, kotlin_df)

    save_classification(connection, db, language_df)


if __name__ == "__main__":
    main()
