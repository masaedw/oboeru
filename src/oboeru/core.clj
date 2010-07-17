(ns oboeru.core
  (:use hiccup.core
        hiccup.page-helpers))

(def words
     [
      ["essere" "avere" "stare" "volere" "dovere" "potere" "andare" "venire" "partire" "arrivare" "tornare" "ritornare"]
      ["salire" "scendere" "entrare" "uscire" "cadere" "passare" "rimanere" "diventare" "crescere" "nascere" "morire" "riuscire"]
      ["sedersi" "alzarsi" "lavarsi" "vestirsi" "svegliarsi" "addormentarsi" "riposarsi" "accomodarsi" "fermarsi" "andarsene" "mettersi" "sbrigarsi"]
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

(defn make-test
  []
  (let [test-words (shuffle (flatten (take 3 words)))]
    (html (doctype :html5)
          [:head
           [:style {:type "text/css"}
            *page-style*]
           ]
          [:body
           (for [page-words (partition 12 test-words)]
             (place-words page-words)
             )]
          )))
