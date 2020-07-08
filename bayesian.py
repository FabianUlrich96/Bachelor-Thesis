from LangClassifier import LangClassifier


def main():
    classifier = LangClassifier()
    classifier.train(open("code.xml").read(), "xml")
    classifier.train(open("code.java").read(), "java")

    #classifier.train(open("code.kt").read(), "kotlin")

    result = classifier.classify(open("totest.java").read())
    print(result)


if __name__ == "__main__":
    main()
