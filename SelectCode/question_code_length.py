from DataBaseConnection.DataBaseConnection import DataBaseConnection

def main():
    database_connection = DataBaseConnection()
    db = DataBaseConnection.connect_database(database_connection)
    table = "answer_code"
    table_df = DataBaseConnection.select_table(database_connection, db, table)

    for chunk in table_df:
        print(chunk)
    snippet = """
    test
    test
    test
    """

    nlines = snippet.count('\n')

    print(nlines)


if __name__ == "__main__":
    main()
