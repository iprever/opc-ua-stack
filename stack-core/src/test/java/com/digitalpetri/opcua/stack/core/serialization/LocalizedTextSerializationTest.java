package com.digitalpetri.opcua.stack.core.serialization;

import com.digitalpetri.opcua.stack.core.types.builtin.LocalizedText;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LocalizedTextSerializationTest extends SerializationFixture {

    @DataProvider
    public Object[][] getLocalizedTexts() {
        return new Object[][]{
                {new LocalizedText(null, null)},
                {new LocalizedText("locale", null)},
                {new LocalizedText(null, "text")},
                {LocalizedText.english("hello, world!")},
        };
    }

    @Test(dataProvider = "getLocalizedTexts", description = "LocalizedText is round-trip serializable.")
    public void testLocalizedText(LocalizedText localizedText) throws Exception {
        encoder.encodeLocalizedText(null, localizedText);
        LocalizedText decoded = decoder.decodeLocalizedText(null);

        assertEquals(decoded, localizedText);
    }

}
