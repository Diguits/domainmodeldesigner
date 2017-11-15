package com.diguits.common.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public abstract class CSVSerializer<T> extends SerializerBase<List<T>> {

    @Override
    protected void innerSerialize(OutputStream targetStream, List<T> list) throws IOException {
        // All you need is to create an instance of CsvWriter with the default
        // CsvWriterSettings.
        // By default, only values that contain a field separator are enclosed
        // within quotes.
        // If quotes are part of the value, they are escaped automatically as
        // well.
        // Empty rows are discarded automatically.
        CsvWriter writer = new CsvWriter(targetStream, StandardCharsets.UTF_8, new CsvWriterSettings());

        // Write the record headers of this file
        writer.writeHeaders(getHeaders());

        for (int i = 0; i < list.size(); i++) {
            T model = list.get(i);
            String[] entries = fillEntriesFromModel(model, i);
            // Here we just tell the writer to write everything and close the
            // given output Writer instance.
            writer.writeRow(entries);

        }
        writer.close();
    }

    protected abstract String[] getHeaders();

    protected Class<List<T>> getModeClass() {
        return null;
    }

    ;

    protected abstract String[] fillEntriesFromModel(T model, int index);

    @Override
    protected List<T> innerDeserialize(InputStream sourceStream) throws IOException {
        List<T> result = new ArrayList<T>();
        CsvParserSettings settings = new CsvParserSettings();
        // the file used in the example uses '\n' as the line separator
        // sequence.
        // the line separator sequence is defined here to ensure systems such as
        // MacOS and Windows
        // are able to process this file correctly (MacOS uses '\r'; and Windows
        // uses '\r\n').
        settings.getFormat().setLineSeparator("\n");

        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);

        parser.beginParsing(sourceStream, StandardCharsets.UTF_8);
        parser.parseNext();
        String[] entries = null;
        Class<T> elementClass = getElementClass();
        int i = 0;
        while ((entries = parser.parseNext()) != null) {
            T model = null;
            try {
                model = elementClass.newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (model != null) {
                fillModelFromEntries(model, entries, i);
                result.add(model);
            }
            i++;
        }
        parser.stopParsing();
        return result;
    }

    protected abstract void fillModelFromEntries(T model, String[] entries, int index);

    protected abstract Class<T> getElementClass();

}
