(ns oboeru.core
  (:use hiccup.core
        hiccup.page-helpers))

(def words
     [
      ["fare" "prendere" "tenere" "mettere" "usare" "abitare" "vivere" "dormire" "pulire" "lavare" "lavorare"]
      ["piacere" "interessare" "occorrere" "importare" "volerci" "succedere" "parere" "sembrare" "bastare" "significare" "servire" "convenire"]
      ["sentirsi" "preoccuparsi" "arrabiarsi" "fregarsene" "divertirsi" "annoiarsi" "chiamarsi" "ricordarsi" "sposarsi" "accorgersi" "raccomandarsi" "rendersi conto"]
      ["sedersi" "alzarsi" "lavarsi" "vestirsi" "svegliarsi" "addormentarsi" "riposarsi" "accomodarsi" "fermarsi" "andarsene" "mettersi" "sbrigarsi"]
      ["salire" "scendere" "entrare" "uscire" "cadere" "passare" "rimanere" "diventare" "crescere" "nascere" "morire" "riuscire"]
      ["essere" "avere" "stare" "volere" "dovere" "potere" "andare" "venire" "partire" "arrivare" "tornare" "ritornare"]
      ]
     )

(defhtml place-words
  [words]
  [:table {:class "exam newpage"}
   [:tr [:td] [:td]]
   (for [[l r] (partition 2 words)]
     [:tr
      [:td l]
      [:td r]
      ])
   ])

(def *page-style*
"
.newpage {
  page-break-after: always;
}

.exam {
  width: 100%;
  height: 25cm; /* B5 257*182mm */
}

.exam td {
  width: 50%;
  font-size: 150%;
  vertical-align: top;
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

(defn new-test
  []
    (page "new-test"
          (let [test-words (shuffle (flatten (take pages-par-test words)))]
            (for [page-words (partition words-par-page test-words)]
              (place-words page-words)
              ))))

(defn random-test
  []
  (page "random-test"
        (let [test-words (shuffle (take (* pages-par-test words-par-page) (flatten words)))]
          (for [page-words (partition words-par-page test-words)]
            (place-words page-words)
            ))))

(defn index
  []
  (page "index"
        (unordered-list
         (list (link-to "/new-test" "new-test")
               (link-to "/random-test" "random-test")))))
