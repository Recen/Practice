package com.recen.learn;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.recen.dotframe.impl.AbstractPageImpl;
import com.recen.learn.practice1.Practice1;
import com.recen.learn.practice1.Practice1Repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class Practice1Test {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    public AbstractPageImpl page;

    @Mock
    public Practice1Repository practice1Repository;

    Practice1 practice1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       practice1 = new Practice1();
        practice1.init(page,practice1Repository);

    }

    @Test
    public void testSendSmsCode() {
        practice1.sendSmsCode();
        verify(page).showMessage("sendSmsCode");
    }
}
