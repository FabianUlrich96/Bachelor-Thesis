from DataBaseConnection.DataBaseConnection import DataBaseConnection
import pandas as pd
j = 0

def connect_db():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    return database_connection, db


def add_function(snippet):
    matches = ["fun main"]
    if any(x in snippet for x in matches):
        if snippet.endswith("\n"):
            pass
        else:
            snippet = snippet + "\n"
    else:
        global j
        j += 1
        str_j = str(j)
        f = open("kotlin_main_added_com.txt", "w", encoding="utf-8")
        f.write(str_j)
        snippet = "fun main{}\n" + snippet

    return snippet


def get_code_snippet():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    kotlin_question_df = DataBaseConnection.select_question_kotlin(database_connection, db)
    kotlin_answer_df = DataBaseConnection.select_answer_kotlin(database_connection, db)
    kotlin_new_answer = pd.DataFrame(columns=['id', 'code_snippet'])
    kotlin_new_question = pd.DataFrame(columns=['id', 'code_snippet'])

    for chunk in kotlin_question_df:
        for row in chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            snippet = add_function(snippet)
            new_row = {'id': snippet_id, 'code_snippet': snippet}
            kotlin_new_question = kotlin_new_question.append(new_row, ignore_index=True)
    for chunk in kotlin_answer_df:
        for row in chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            snippet = add_function(snippet)
            new_row = {'id': snippet_id, 'code_snippet': snippet}
            kotlin_new_answer = kotlin_new_answer.append(new_row, ignore_index=True)

    return kotlin_new_answer, kotlin_new_question


def save_code_df(java_new_answer, java_new_question):
    database_connection, db = connect_db()
    DataBaseConnection.save_kotlin_answer_update(database_connection, db, java_new_answer)
    DataBaseConnection.save_kotlin_question_update(database_connection, db, java_new_question)


def main():
    kotlin_new_answer, kotlin_new_question = get_code_snippet()
    save_code_df(kotlin_new_answer, kotlin_new_question)


if __name__ == "__main__":
    main()
