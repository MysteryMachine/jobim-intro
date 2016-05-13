(ns intro.blog
  (:require [jobim.core :as jobim])
  (:require-macros [jobim.core :as jobim]))

(def show-state (jobim/new-show))

(jobim/defclj omg-brands
  80
  (def clicks (atom 0))
  (defn ^:export render []
    (let [brand-color (str "#" (mod @clicks 9) "00")]
      [:div
       {:style {:color brand-color
                :font-size (str (+ 1 (/ (mod @clicks 9) 3)) "em")}}
       [:div "BUY OUR BRAND'S PRODUCTS"]
       [:button {:on-click (fn [] (swap! clicks inc))}
        "HOLY LORD THESE BRANDS"]])))

(jobim/defclj div
  80
  (defn component [] [:div "Hello world!"]))

(jobim/defclj styled-div
  80
  (defn component []
    [:div#id.class {:style {:font-size "2em"}}
     [:a {:href "http://www.adaptly.com" :target "_none"} "I'm cool now"]]))

(jobim/defclj button
  90
  (defn component []
    [:button
     {:on-click (fn [] (js/window.alert "hello!"))
      :style {:color "white"
              :background-color "blue"
              :font-size "2em"}}
     "Alert everyone."]))

(jobim/defblog my-blog
  show-state
  (jobim/title
   [:div {:style {:font-weight "bold" :font-size "1.5em"}}
    "Clojure" [:span "script"] " for React Fans"]
   [:div "Written by Sal Becker for Adaptly"])
  (jobim/text
   "React was a game changer for frontend development. Never before, in the history of "
   "web development, has a declarative way of building complex interactive applications "
   "been so popular. React allows developers to leverage the power of functional programming, "
   "immutability, and purity in their day to day, and lets them go " [:strong "FASTER "] "while "
   "using those constructs. What a time to be alive.")
   (jobim/text
    "But it is not all roses and responsive, well written, web applications, is it? React has its "
    "share of downsides, like any other technology does. Particularly, React components are "
    "rather cumbersome to define. One may find themselves writing a rather large amount of boilerplate "
    "relatively often. This amount of boilerplate increases significantly when one decides to use "
    "immutable data structures. One simply has to bite the bullet when using these constructs, as "
    "there is no clean way to define concise accessors and constructors for these data structures "
    "in vanilla Javascript.")
  (jobim/text
   "So why Clojure? Clojure's strength in the React ecosystem was so great, that it actually affected "
   "the way that React code was written in the Javascript world. Clojure has a few major strengths "
   "when it comes to interfacing with React. The first important thing to know is that "
   "Clojure data structures are idiomatically immutable. "
   "This means that its data structures are both immutable by default and that"
   "the langage is designed to provide first class support for the access and modification of these data structures."
   "In the code below, we create a list, a vector (like an immutable array), a hash-map (like an immutable Object), "
   "and a hash-set (like a list that can only contain unique entries, respectively.")
  (jobim/clojure-code 40
    (list 1 2 3 4 5)
    (vector 1 2 3 4 5)
    (hash-map :a 1 :b 2)
    (hash-set 1 2 3 4))
  (jobim/text "And the literal versions of these calls (the code above and the code below are equivalent).")
  (jobim/clojure-code 40
    '(1 2 3 4 5)
    [1 2 3 4 5]
    {:a 1 :b 2}
    #{1 2 3 4})
  (jobim/text
   "The creation of these data structures is not the only thing that is easy in Clojure. The use of these "
   "data structures is also a first class consideration in Clojure. Consider these use cases:")
  (jobim/custom-slide
   (fn [& _]
     (into [:ul] (map (fn [i] (into [:li] i)))
           [["To append to the end of a vector, list, or set, we use " (jobim/inline (conj data-strucure 1)) ". "]
            ["To grab the first item of a list or vector, we use " (jobim/inline (first data-structure)) ". "]
            ["To randomly access something within a vector, we use " (jobim/inline (nth my-vector 3)) ". "]
            ["To access something within a hash-map, we use " (jobim/inline (get my-hash-map :a)) ". "]
            ["To add something to a map we use " (jobim/inline (assoc my-hash-map :new-entry 1))]])))
  (jobim/text
   "Additionally, Clojure offers hygenic, homoiconic macros to reduce boilerplate. "
   "Macros are an advanced topic, but Clojure macros offer a safe way to define new language constructs "
   "This is more that simple string manipulation. More precisely, in Clojure, you are given "
   "access to the AST of the code your macro is modifying. You modify it like a tree. "
   "Using these new language constructs, one is able to get rid of boilerplate. "
   "This means React code written in Clojure much is smaller than a JS equivalent.")
  (jobim/text
   "So what does React look like in Clojure? "
   "The most popular React binding is called "
   [:a
    {:href "https://github.com/reagent-project/reagent"
     :target "_blank"}
    "Reagent"]
   ". Let's write some components. The code below defines a Reagent component. It does so, by defining a "
   "plain Clojure function using " [:strong "defn"] ". This defines a function in our working namespace. ")
  div
  (jobim/text
   "Astute reads may notice that the function is merely returning a vector, with a keyword and a string "
   "inside (for those not in the know, keywords are a datatype in Clojure intended for fast, string-like "
   "comparisons). This is the power of Reagent. This style of writing markup is very common in the Clojure world, "
   "it is something we call Hiccup notation. You can see what the result of that rendering looks like below ("
   "and by result, I mean the result below is literally that function being rendered by Reagent, on this webpage).")
  (jobim/component div)
  (jobim/text
   "Hiccup can do just more than render a single div. If the second element of a hiccup form is a "
   "hash-map, it is used to set the element's properties. Additionally, one can set ids and classes "
   "in much the same way they can in CSS. As a final neat thing, this syntax can be used to set CSS in a "
   "very powerful way. Using hashmaps for style isn't always the best idea, but they're a great way of doing "
   "things programatically when you have to. Additionally, you're able to do things like merge different "
   "style maps together, or filter out certain styles.")
  styled-div
  (jobim/component styled-div)
  (jobim/text
   "But enough with static, motionless components that just show things. Let's see Reagent actually in action. "
   "The " (jobim/inline (js/window.alert "hello!")) " form you're seeing below is Clojure's interop form. "
   "By using this syntax, Clojure users can call Javascript functions. The " (jobim/inline (fn [] ...))
   " syntax around it is Clojure's anonymous function syntax.")
  button
  (jobim/component button)
  omg-brands
  (jobim/component
   (jobim/clojure-code 80
    (defn component [state]
      (let [brand-color (str "#" (mod (:i state) 9) "00")]
        [:div
         {:style {:color brand-color
                  :font-size (str (+ 1 (/ (mod (:i state) 9) 3)) "em")}}
         [:div "BUY OUR BRAND'S PRODUCTS"]
         [:button {:on-click (fn [] (swap! show-state (fn [s] (update-in s [:i] inc))))}
          "HOLY LORD THESE BRANDS"]]))))
  (jobim/text
   "Could I integrate Reagent into one of my existing JS projects? "
   "Easily! Reagent contains a reactify-component function, that returns an "
   "adapter that may be used by React. A single argument, props, can then "
   "then be passed to that React component. If you're using npm, you're in "
   "even better shape, as it can also transpile the cljs for you.")
  (jobim/text
   "That is the power of Clojure. If you're already using JSX and other transpilation "
   "technologies in your codebase, why not give Clojure a try? Clojure will happily "
   "play nice with the components already existant in your application. So, remember "
   "next time you need a complex segment of your codebase to run blazingly fast, to be testable, and to "
   "just generally be cool as all heck, the future is the past—use a lisp, today!"))
