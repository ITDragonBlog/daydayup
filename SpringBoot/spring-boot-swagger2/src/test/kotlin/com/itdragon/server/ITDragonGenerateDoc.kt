package com.itdragon.server

import io.github.robwin.markup.builder.MarkupLanguage
import io.github.robwin.swagger2markup.Swagger2MarkupConverter
import org.junit.Test

class ITDragonGenerateDoc {

    private val outputDir = "generated/swagger-ui"

    /**
     * 第一步：./gradlew generateSwaggerDocumentation
     * 第二步：./gradlew test --tests *ITDragonGenerateDoc
     */
    @Test
    @Throws(Exception::class)
    fun doIt() {
        Swagger2MarkupConverter.from("$outputDir/swagger.json")
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .build()
                .intoFolder(outputDir)
    }
}