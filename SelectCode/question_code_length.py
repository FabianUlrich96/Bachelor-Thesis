from DataBaseConnection.DataBaseConnection import DataBaseConnection


def delete_code(df):
    for row in df.itertuples():
        index = row[0]
        id = row[1]
        code_snippet = row[3]
        nlines = code_snippet.count('\n')
        if nlines < 2:
            df = df.drop(index=index)
            print(code_snippet)
    print(df)
    return df


def main():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    question_code_select = DataBaseConnection.select_question_code(database_connection, db)

    for chunk in question_code_select:
        chunk.set_index('id')

        question_final_df = delete_code(chunk)
        DataBaseConnection.save_final_question_code(database_connection, db, question_final_df)


if __name__ == "__main__":
    main()
