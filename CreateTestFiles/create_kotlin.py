from CreateTestFiles.FileCreation import FileCreation
from DataBaseConnection.DataBaseConnection import DataBaseConnection
import re
import random
import string
import os

j = 0
i = 0


def get_code_snippet(file_name, extension, file):
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    kotlin_question_df = DataBaseConnection.select_question_kotlin(database_connection, db)
    kotlin_answer_df = DataBaseConnection.select_answer_kotlin(database_connection, db)

    for chunk in kotlin_question_df:
        for row in chunk.itertuples():
            post_id = str(row[1])
            snippet = row[2]
            append_file(file_name, extension, snippet, post_id, file)
    for chunk in kotlin_answer_df:
        for row in chunk.itertuples():
            post_id = str(row[1])
            snippet = row[2]
            append_file(file_name, extension, snippet, post_id, file)


def random_char(y):
    return ''.join(random.choice(string.ascii_letters) for x in range(y))


def append_file(file_name, extension, snippet, post_id, file):
    size = FileCreation.file_size(file_name + extension)

    if os.stat(file_name + extension).st_size == 0:
        file.write("fun main(){}\n")

    if size < 95:
        if "fun main" in snippet:
            function_name = random_char(5)
            sub_main = re.sub(r'fun main', "fun " + function_name, snippet)
            file.write("\n//ID: " + post_id + "\n" + sub_main)
            file.flush()
        else:
            global j
            j += 1
            str_j = str(j)
            f = open(file_name + "_added" + extension, "w", encoding="utf-8")
            f.write(str_j)
            snippet = "\n" + snippet + "\n}"
            file.write("\n//ID: " + post_id + snippet)
            file.flush()

    else:
        global i
        i += 1
        file.close()
        str_i = str(i)
        file_name = file_name[:-1]
        FileCreation.create_file(file_name + str_i, extension)
        append_file(file_name + str_i, extension, snippet, post_id, file)


def main():
    extension = ".kt"
    file_name = "kotlin_code"
    file = FileCreation.create_file(file_name, extension)

    get_code_snippet(file_name, extension, file)


if __name__ == "__main__":
    main()
