package pengyanb.com.myktapp

import io.mockk.every
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CalculatorTests {
    val testSubject = TestSubject()

    @Test
    fun `private property test`() {
        val mockedTestSubject = spyk<TestSubject>(recordPrivateCalls = true)
        every { mockedTestSubject.publicString } answers { fieldValue + " mocked"}
        every { mockedTestSubject getProperty "publicString2" } propertyType String::class answers { fieldValue + " mocked" }
        every { mockedTestSubject["anPrivateMethodToMock"]() } returns "Mocked Private function"
        System.out.println(mockedTestSubject.publicString)
        System.out.println(mockedTestSubject.publicString2)
        System.out.println(mockedTestSubject.testGetPrivateString())
        System.out.println(mockedTestSubject.checkPrivateMethodValue())
        assertEquals(mockedTestSubject.publicString, "Public String mocked")
    }

    @Test
    fun `1 + 1 = 2`() {
        val calculator = Calculator()
        assertEquals(2, calculator.add(1,1), "1 + 1 should equal 2")
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(
        "0,    1,   1",
        "1,    2,   3",
        "49,  51, 100",
        "1,  100, 101"
    )
    fun add (first: Int, second: Int, expectedResult: Int) {
        val calculator = Calculator()
        assertEquals(expectedResult, calculator.add(first, second)) {
            "$first + $second should equal $expectedResult"
        }
    }

    @Test
    fun divisionByZeroError() {
        val calculator = Calculator()
        val exception = assertThrows<AssertionError> {
            calculator.div(1, 0)
        }
        assertEquals("Division by Zero", exception.message)
    }
}