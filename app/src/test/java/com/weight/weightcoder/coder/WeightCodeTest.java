package com.weight.weightcoder.coder;

import android.text.TextUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class WeightCodeTest {

    @Before
    public void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
    }

    @Test
    public void code() {
        //Todo: Does not work properly in test, but works fine in the app
        assertEquals("== == :[==]: :==: == == |==| [==] == == :[==]: :[==]:", WeightCode.code("ASD"));
    }

    @Test
    public void decode() {
        try {
            assertEquals("ASD", WeightCode.decode("== == :[==]: :==: == == |==| [==] == == :[==]: :[==]:"));
        } catch (Exception e) {
            assertEquals(false, true);
        }
    }
}