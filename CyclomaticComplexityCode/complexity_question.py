from DataBaseConnection import DataBaseConnection
from CyclomaticComplexity import CyclomaticComplexity


def connect_db():
    database_connection = DataBaseConnection.DataBaseConnection()
    db = DataBaseConnection.DataBaseConnection.connect_database(database_connection)
    return db, database_connection


def get_question(database_connection, db):
    kotlin_df = DataBaseConnection.DataBaseConnection.select_question_kotlin(database_connection, db)
    java_df = DataBaseConnection.DataBaseConnection.select_question_java(database_connection, db)
    return kotlin_df, java_df


def save_complexity(connection, db, kotlin_complexity, java_complexity):
    DataBaseConnection.DataBaseConnection.save_kotlin_question_complexity(connection, db, kotlin_complexity)
    DataBaseConnection.DataBaseConnection.save_java_question_complexity(connection, db, java_complexity)


def main():
    db, database_connection = connect_db()
    kotlin_df, java_df = get_question(database_connection, db)
    kotlin_complexity, java_complexity = CyclomaticComplexity.CyclomaticComplexity().test_complexity(kotlin_df, java_df)
    save_complexity(database_connection, db, kotlin_complexity, java_complexity)


if __name__ == "__main__":
    main()
