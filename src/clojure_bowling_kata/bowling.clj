(ns clojure-bowling-kata.bowling
  (:require [clojure.string :as st]))

(defn- convert-bowl [bowl]
  (case bowl
    "X" 10
    "/" 10
    (Integer. bowl)))

(defn- frame-score [bowl frame bowls]
  (if (and (< frame 10) (= 10 bowl))
    (+ bowl (nth bowls (+ 1 frame)) (nth bowls (+ 2 frame)))
    bowl))

;(defn score [bowls]
;  (let [bowls-as-list (st/split bowls #"")
;        bowls-converted (map #(convert-bowl %) bowls-as-list)]
;    (reduce + (map #(frame-score % (.indexOf bowls-converted %) bowls-converted) bowls-converted))))

(defn value-of-frame [ required-frame current-frame bowls]
  (filter #(= (first %) (+ required-frame current-frame)) bowls))

(defn score-strike [current-frame bowls]
  (+ 10 (value-of-frame 1 current-frame bowls) (value-of-frame 2 current-frame bowls)))

;(defn convert-score [bowl]
;  (cond
;    (= "X" (str (last bowl)) 10)
;    (= "/" bowl) 10)
;    :else bowl)

;; (str (last bowl))) (score-strike (first bowl) bowls)

(defn lookup [bowl-score]
  (cond
    (= "X" bowl-score) 10))

(defn next-frame [required-frame current-frame bowls-map]
  (let [frame (flatten (filter #(= (first %) (+ current-frame required-frame)) bowls-map))
        score-for-frame (lookup (str (last frame)))]
    (if (nil? score-for-frame) 0 score-for-frame)))

(defn score-for-strikes [strikes bowls-map]
  (let [strike-scores (map #(+ (lookup (str (last %))) (if (< (first %) 9 )
                                                         (+ (next-frame 1 (first %) bowls-map) (next-frame 2 (first %) bowls-map))
                                                         0 ))  strikes)]
    strike-scores))

(defn score [bowls]
  (let [bowls-map (map-indexed vector bowls)
        strikes (filter #(= (str (last %)) "X") bowls-map)
        spares (filter #(= (last %) "/") bowls-map)
        normal (filter #(not (= "X" %)) (st/split bowls #""))
        score-for-normal (reduce + (map #(Integer/parseInt %) normal))]
    (println "Strikes" strikes)
    (println "Normal" normal)
    (println "Scores for strikes" (score-for-strikes strikes bowls-map))
    (println "Scores for normal" score-for-normal)

    (+ (reduce + (score-for-strikes strikes bowls-map)) score-for-normal)))
