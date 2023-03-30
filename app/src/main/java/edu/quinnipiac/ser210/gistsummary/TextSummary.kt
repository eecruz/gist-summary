package edu.quinnipiac.ser210.gistsummary

data class TextSummary(var summary: String?)
data class SummarySpecs(var text: String?, var num_sentences: Int?, var is_detailed: Boolean?)