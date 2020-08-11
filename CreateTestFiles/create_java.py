from CreateTestFiles.FileCreation import FileCreation
from DataBaseConnection.DataBaseConnection import DataBaseConnection

j = 0
i = 0


def get_code_snippet(file_name, extension, file):
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    java_question_df = DataBaseConnection.select_question_java(database_connection, db)
    java_answer_df = DataBaseConnection.select_answer_java(database_connection, db)

    for chunk in java_question_df:
        for row in chunk.itertuples():
            post_id = str(row[1])
            snippet = row[2]
            append_file(file_name, extension, snippet, post_id, file)
    for chunk in java_answer_df:
        for row in chunk.itertuples():
            post_id = str(row[1])
            snippet = row[2]
            append_file(file_name, extension, snippet, post_id, file)


def append_file(file_name, extension, snippet, post_id, file):
    size = FileCreation.file_size(file_name + extension)
    tokens_list = ["import", "package", "class"]
    if size < 95:
        if any(ext in snippet for ext in tokens_list):
            file.write("\n//ID: " + post_id + "\n" + snippet)
            file.flush()
        else:
            global j
            j += 1
            str_j = str(j)
            f = open(file_name + "_added" + extension, "w", encoding="utf-8")
            f.write(str_j)
            snippet = "\nclass Snippet{ \n" + snippet + "\n}"
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
    extension = ".java"
    file_name = "java_code"
    file = FileCreation.create_file(file_name, extension)

    get_code_snippet(file_name, extension, file)


if __name__ == "__main__":
    main()
