class LangClassifier:
    """
    Inspired by https://github.com/glaslos/langdog
    """

    def __init__(self):
        self.data = {}
        self.totals = {}
        self.words = {}

    def detect_words(self, code):
        word_list = code.split()
        tag_list = code.split()
        for semicolon in code:
            if semicolon == ";":
                word_list.append(semicolon)
        for tag in tag_list:
            if "</" in tag:
                word_list.append("</")
        self.words = word_list
        return filter(bool, word_list)

    def train(self, code, lang):
        self.data[lang] = {}
        for word in self.detect_words(code):
            if word in self.data[lang]:
                self.data[lang][word] += 1
            else:
                self.data[lang][word] = 1
            if word in self.totals:
                self.totals[word] += 1
            else:
                self.totals[word] = 1

    def prob(self, words, lang):
        len_words = len(self.words)
        res = 0.0
        for word in words:
            try:

                res = res + self.totals[word] / self.data[lang][word]
            except KeyError:
                continue
        if len_words > 0:
            res = res/len_words
        else:
            res = 0
        return res

    def classify(self, code):
        lang_prob = {}
        words = self.detect_words(code)
        for lang in iter(self.data.keys()):
            prob = self.prob(words, lang)
            lang_prob[prob] = lang

        return lang_prob
