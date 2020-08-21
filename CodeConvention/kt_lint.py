import subprocess
from DataBaseConnection.DataBaseConnection import DataBaseConnection
import pandas as pd


def get_connection():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    return database_connection, db


def temp_file(snippet):
    f = open("kotlin_fun.kt", "w", encoding="utf-8")
    f.write(snippet)
    f.close()


def ktlint_cmd():
    cmd = "ktlint kotlin_fun.kt"
    proc = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    out, err = proc.communicate()
    str_out = str(out)
    kotlin_file_error = "Not a valid Kotlin file"
    indentation_error = "Unexpected indentation"
    semicolon_error = "Unnecessary semicolon"
    wildcard_error = "Wildcard import"
    blank_lines_error = "Unnecessary block"
    whitespace_error = "Unnecessary space"
    return_error = "Unnecessary \"Unit\" return"
    redundant_error = "Redundant"
    break_error = "Line break before assignment is not allowed"
    indentation_error_count = str_out.count(indentation_error)
    semicolon_error_count = str_out.count(semicolon_error)
    wildcard_error_count = str_out.count(wildcard_error)
    blank_lines_error_count = str_out.count(blank_lines_error)
    whitespace_error_count = str_out.count(whitespace_error)
    return_error_count = str_out.count(return_error)
    redundant_error_count = str_out.count(redundant_error)
    break_error_count = str_out.count(break_error)
    kotlin_file_error_count = str_out.count(kotlin_file_error)

    return indentation_error_count, semicolon_error_count, wildcard_error_count, blank_lines_error_count, whitespace_error_count, return_error_count, redundant_error_count, break_error_count, kotlin_file_error_count


def get_snippet():
    kotlin_answers = pd.DataFrame(columns=['id', 'code_snippet', 'valid_file', 'indention_error', 'semicolon_error', 'wildcard_error', 'blank_line_error', 'whitespace_error', 'return_error', 'redundant_error', 'break_error'])
    kotlin_questions = pd.DataFrame(columns=['id', 'code_snippet', 'valid_file', 'indention_error', 'semicolon_error', 'wildcard_error', 'blank_line_error', 'whitespace_error', 'return_error', 'redundant_error', 'break_error'])

    connection, db = get_connection()
    answer_df = DataBaseConnection.select_kotlin_answer_update(connection, db)
    question_df = DataBaseConnection.select_kotlin_question_update(connection, db)
    for kotlin_chunk in answer_df:
        for row in kotlin_chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            temp_file(snippet)
            indentation_error_count, semicolon_error_count, wildcard_error_count, blank_lines_error_count, whitespace_error_count, return_error_count, redundant_error_count, break_error_count, kotlin_file_error_count = ktlint_cmd()
            if kotlin_file_error_count != 0:
                kotlin_file = False
            else:
                kotlin_file = True
            new_row = {'id': snippet_id, 'code_snippet': snippet, 'valid_file': kotlin_file, 'indention_error': indentation_error_count, 'semicolon_error': semicolon_error_count, 'wildcard_error': wildcard_error_count, 'blank_line_error': blank_lines_error_count, 'whitespace_error': whitespace_error_count, 'return_error': return_error_count, 'redundant_error': redundant_error_count, 'break_error': break_error_count}
            kotlin_answers = kotlin_answers.append(new_row, ignore_index=True)

    for kotlin_chunk in question_df:
        for row in kotlin_chunk.itertuples():
            snippet_id = row[1]
            snippet = row[2]
            temp_file(snippet)
            indentation_error_count, semicolon_error_count, wildcard_error_count, blank_lines_error_count, whitespace_error_count, return_error_count, redundant_error_count, break_error_count, kotlin_file_error_count = ktlint_cmd()
            if kotlin_file_error_count != 0:
                kotlin_file = False
            else:
                kotlin_file = True
            new_row = {'id': snippet_id, 'code_snippet': snippet, 'valid_file': kotlin_file, 'indention_error': indentation_error_count, 'semicolon_error': semicolon_error_count, 'wildcard_error': wildcard_error_count, 'blank_line_error': blank_lines_error_count, 'whitespace_error': whitespace_error_count, 'return_error': return_error_count, 'redundant_error': redundant_error_count, 'break_error': break_error_count}
            kotlin_questions = kotlin_questions.append(new_row, ignore_index=True)
    return kotlin_answers, kotlin_questions


def save_df(answer_df, question_df):
    connection, db = get_connection()
    DataBaseConnection.save_ktlint_answers(connection, db, answer_df)
    DataBaseConnection.save_ktlint_questions(connection, db, question_df)


def main():
    answer_df, question_df = get_snippet()
    save_df(answer_df, question_df)


if __name__ == "__main__":
    main()
