(ns nextjournal.markdown
  (:require ["markdown" :as md]
            ["markdown-it/lib/token" :as Token]
            ["katex" :as katex]
            [applied-science.js-interop :as j]
            [nextjournal.markdown.data :as markdown.data]))

(extend-type Token
  ILookup
  (-lookup [this key] (j/get this key)))

(def tokenize md/parse)
(def tokenize-j md/parse)
(defn parse [markdown-text] (-> markdown-text tokenize markdown.data/<-tokens))
(def render md/render)

(comment
  (render "# Hello Markdown\nWhat's _going_ on?")
  (js/console.log (tokenize "# Hello Markdown\nWhat's _going_ on?"))
  (js/console.log
   (parse "# Hello Markdown

[[TOC]]

- what
- a [nice](very/nice/thing)
- ~~thing~~
"))
  )
