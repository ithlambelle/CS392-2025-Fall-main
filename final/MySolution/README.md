work summary
- final_00..final_05 implemented in this folder using only my code from myfinallib.
- word parsing built in finalutil (char stream -> lowercased word stream) and reused across all tasks.
- final_01: word streamization from character stream, handles word boundaries and case normalization.
- final_02: collect words into array, quicksort with assign06_03, count consecutive duplicates, then mergesort by frequency (descending) then lexicographic order.
- final_03: use assign08_02 quadratic-probing hash map as multimap; each word occurrence inserted as value, frequency = length of value list per key. large capacity (600000) to minimize collisions.
- final_04: generalize quiz02_06 into generic randomized bst map; standard get/put counting pattern (get current count, increment, put back).
- final_05: stable n-way merge using mypqqueuearray min-heap (tie-break by original list index for stability), then stable 5-way mergesort on lnlist with node reuse via unlink1/link1.

notes on changes to earlier submissions
- quiz02_06 reworked into a generic (key,value) randomized bst map with put/get/foreach and tolist helpers so it can serve as the required map for final_04.
- myfinallib now contains the packaged Library code plus my assignment utilities (assign05_01, assign06_03, assign08_02, quiz02_06 map).
- all solutions reuse finalutil for consistent word parsing and case handling.

paths
- kept the provided pg2701 path in final_00 (../Data/pg2701.txt) as given.

compile/run hints
- use the bundled jdk at /opt/homebrew/opt/openjdk/bin (e.g., /opt/homebrew/opt/openjdk/bin/javac -cp .:../MyFinalLib Final_0*.java).
