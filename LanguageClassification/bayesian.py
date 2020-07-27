import pandas as pd


def process_df(df, classifier):
    for chunk in df:

        code_df = pd.DataFrame(columns=['id', 'code_snippet', 'language'])
        for row in chunk.itertuples():
            post_id = row[1]
            snippet = row[2]
            result = classifier.classify(snippet)
            for value, key in result.items():
                new_row = {'id': post_id, 'code_snippet': snippet, 'propability': value}
                code_df = code_df.append(new_row, ignore_index=True)
        return code_df


def train_java(classifier, df):
    classifier.train(open("../java_training.java", encoding="utf-8").read(), "java")
    df = process_df(df, classifier)
    return df


def train_kotlin(classifier, df):
    classifier.train(open("../kotlin_training.kt", encoding="utf-8").read(), "kotlin")
    df = process_df(df, classifier)
    return df


def train_xml(classifier, df):
    classifier.train(open("../xml_training.xml", encoding="utf-8").read(), "xml")
    df = process_df(df, classifier)
    return df
