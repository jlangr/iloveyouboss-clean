### Domain overview
A Question consists of text and type (e.g. boolean -> yes/no)
Questions are uniquely identified by their ID. (The ID is likely generated in some service-level code.)
An answer is a value wrapper.

A Profile is a bunch of answers (to Questions). eg: Does a relocation package exist -> yes
A Profile might not have an answer to a given Question.

A Criterion is a Question plus the desired Answer to that question.
Criterion might be optional (not implemented yet!).
An interested party sets up criteria ("many criterion") to determine to what extent they match a profile.
