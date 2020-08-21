from DataBaseConnection.DataBaseConnection import DataBaseConnection
import pandas as pd
j = 0


def connect_db():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    return database_connection, db


def add_class(snippet):
    tokens_list = ["import", "package", "class"]
    if any(ext in snippet for ext in tokens_list):
        pass
    else:
        global j
        j += 1
        snippet = "class Snippet { \n" + snippet + "\n}"
    return snippet


def get_code_snippet():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    java_question_df = DataBaseConnection.select_question_java(database_connection, db)
    java_answer_df = DataBaseConnection.select_answer_java(database_connection, db)
    java_new_answer = pd.DataFrame(columns=['id', 'code_snippet'])
    java_new_question = pd.DataFrame(columns=['id', 'code_snippet'])

    for chunk in java_question_df:
        for row in chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            snippet = add_class(snippet)
            new_row = {'id': snippet_id, 'code_snippet': snippet}
            java_new_question = java_new_question.append(new_row, ignore_index=True)

    for chunk in java_answer_df:
        for row in chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            snippet = add_class(snippet)
            new_row = {'id': snippet_id, 'code_snippet': snippet}
            java_new_answer = java_new_answer.append(new_row, ignore_index=True)

    return java_new_answer, java_new_question


def save_code_df(java_new_answer, java_new_question):
    database_connection, db = connect_db()
    DataBaseConnection.save_java_answer_update(database_connection, db, java_new_answer)
    DataBaseConnection.save_java_question_update(database_connection, db, java_new_question)


def main():
    java_new_answer, java_new_question = get_code_snippet()
    save_code_df(java_new_answer, java_new_question)
    global j
    str_j = str(j)
    f = open("java_class_added_com.txt", "w", encoding="utf-8")
    f.write(str_j)


if __name__ == "__main__":
    main()
