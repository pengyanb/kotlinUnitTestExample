package pengyanb.com.myktapp

class TestSubject {
    private val privateString = "Private String"
    val publicString = "Public String"
    var publicString2 = "Public String2"

    private fun anPrivateMethodToMock(): String {
        return "An Private Method To Mock: ${privateString})"
    }

    fun checkPrivateMethodValue(): String {
        return anPrivateMethodToMock()
    }

    fun testGetPrivateString(): String {
        return "testGetPrivateString: ${privateString}"
    }

    fun testGetPublicString(): String {
        return "testGetPublicString: ${publicString}"
    }
}