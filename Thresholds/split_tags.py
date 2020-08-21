import pandas as pd


def read_csv():
    csv_file = pd.read_csv("ru_alltags.csv")

    return csv_file


def save_csv(tags_list):

    tags_df = pd.DataFrame(tags_list, columns=['tags'])
    tags_df.to_csv("ru_allquestion_tags_split.csv", index=False)
    unique_df = tags_df.drop_duplicates(keep="first", inplace=False)
    unique_df.to_csv("ru_allquestions_unique.csv", index=False)


def split_string(csv_file):
    tags_list = []
    for index, row in csv_file.iterrows():
        row_str = row['tags']
        split_row = row_str.split("<")
        for item in split_row:
            tags_list.append(item)
    return tags_list


def main():
    csv_file = read_csv()
    tags_list = split_string(csv_file)
    save_csv(tags_list)


if __name__ == "__main__":
    main()