(ns oboeru.core
  (:use hiccup.core
        hiccup.page-helpers))

(declare *words*)

(defn load-words
  [name]
  (def *words* (load-file name)))

(defhtml place-words
  [words]
  [:table {:class "exam"}
   (for [[l r] (partition-all 2 words)]
     [:tr
      [:td l]
      [:td r]
      ])
   ])

(defhtml newpage
  []
  [:div {:class "newpage"}])

(def *page-style*
"
.newpage {
  page-break-after: always;
}

.exam {
  /* B5 182*257mm */
  width: 18cm;
  height: 25cm;
  border-collapse: collapse;
  border-spacing: 0;
}

.exam td {
  width: 50%;
  font-size: 150%;
  vertical-align: top;
  border: 1px solid #e3e3e3;
}
"
)

(def words-par-page 12)
(def pages-par-test 3)

(defn page
  [title & body]
  (html (doctype :html5)
        [:head
         [:style {:type "text/css"}
          *page-style*]
         [:title title]
         ]
        [:body
         body])
  )

(defn quiz-page
  [title & quizzes]
  (page title (apply interpose (newpage) quizzes)))

(defn new-test-series
  []
  (quiz-page "new-test (in series)"
             (let [test-words (flatten (reverse (take pages-par-test *words*)))]
               (for [page-words (partition-all words-par-page test-words)]
                 (place-words page-words)
                 ))))


(defn new-test-random
  []
  (quiz-page "new-test (in random)"
             (let [test-words (shuffle (flatten (take pages-par-test *words*)))]
               (for [page-words (partition-all words-par-page test-words)]
                 (place-words page-words)
                 ))))

(defn random-test
  []
  (quiz-page "random-test"
             (let [test-words (shuffle (take (* pages-par-test words-par-page) (flatten *words*)))]
               (for [page-words (partition-all words-par-page test-words)]
                 (place-words page-words)
                 ))))

(defn index
  []
  (page "index"
        (unordered-list
         (list (link-to "/new-test-series" "new-test (in series)")
               (link-to "/new-test-random" "new-test (in random)")
               (link-to "/random-test" "random-test")))))
