package com.luxoft.recruitment.cstr;

import com.luxoft.recruitment.cstr.datalayer.BlackListProvider;
import com.luxoft.recruitment.cstr.filter.BlackListFilter;
import com.luxoft.recruitment.cstr.http.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlackListFilterTest {

    @Mock
    private BlackListProvider provider;

    private BlackListFilter blackListFilter;

    @Before
    public void init() {
        when(provider.getBlackList()).thenReturn(Arrays.asList("172.16.1.76", "172.16.1.75"));
        blackListFilter = new BlackListFilter(provider);
    }

    @Test
    public void should_block_ip(){
        Request request = new Request("172.16.1.75");
        boolean response = blackListFilter.shouldBlock(request);
        assertTrue(response);
    }

    @Test
    public void should_not_block_ip(){
        Request request = new Request("172.16.1.77");
        boolean response = blackListFilter.shouldBlock(request);
        assertFalse(response);
    }

    @Test
    public void should_add_new_ip() {
        String ip = "172.16.1.77";
        blackListFilter.addIp(ip);

        Request request = new Request(ip);
        assertTrue(blackListFilter.shouldBlock(request));
    }
}
