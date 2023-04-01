package edu.quinnipiac.ser210.gistsummary

data class TextSummary(var summary: Array<String?>?, var article_text: String?, var article_title: String?,
                       var article_authors: Array<String?>?, var article_image: String?, var article_pub_date: String?,
                       var article_url: String?, var article_html: String?, var article_abstract: String?)
data class SummarySpecs(var text: String?, var num_sentences: Int?, var is_detailed: Boolean?)