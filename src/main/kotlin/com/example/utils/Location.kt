package com.example.utils

enum class Location(val location: String) {
    santiago("CL"),
    zurich("CH"),
    auckland("NZ"),
    sidney("AU"),
    londres("UK"),
    georgia("USA");

    fun descripcion(): String {

        return when (this) {

            santiago -> "-33.42205460590072, -70.66008535567657"
            zurich -> "47.40136104401261, 8.513730527845198"
            auckland -> "-36.670257823085706, 174.64977658676"
            sidney -> "-36.79688737235697, 174.65522256860274"
            londres -> "51.52397870307866, -0.12105636431674895"
            georgia -> "32.68589872586188, -83.57680248305265"
        }
    }
}