package ro.msg.learning.shop.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageConverter extends AbstractGenericHttpMessageConverter<Object> {
    protected final CSVConverter<Object> csvConverter;

    public MessageConverter() {
        super(new MediaType("text", "csv"));

        this.csvConverter = new CSVConverter<>();
    }


    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws HttpMessageNotWritableException, IOException {

        List<Object> arrayList;

        if (o instanceof List)
            arrayList = new ArrayList<>((ArrayList<Object>) o);
        else {
            arrayList = Collections.singletonList(o);
        }

        csvConverter.toCsv((Class<Object>) arrayList.get(0).getClass(), arrayList, httpOutputMessage.getBody());
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws HttpMessageNotReadableException, IOException {
        return csvConverter.fromCsv(aClass, httpInputMessage.getBody());
    }

    @Override
    public Object read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws HttpMessageNotReadableException, IOException {
        return readInternal(aClass, httpInputMessage);
    }
}
