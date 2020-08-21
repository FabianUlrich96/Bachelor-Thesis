import pandas as pd


def read_csv():
    csv_file = pd.read_csv("ru_allquestion_tags_split.csv")

    return csv_file


def count_csv(csv):
    counted_csv = csv['tags'].value_counts()
    counted_csv.to_csv("ru_counted_tags.csv")


def main():
    csv = read_csv()
    count_csv(csv)


if __name__ == "__main__":
    main()