package abanoub.johnny.development.moviesapp.modules.searchresults.mvp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SearchResultsViewModelTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    int page = 2,totalpages = 1;
    private SearchResultsViewModel searchResultsViewModel;
    @Before
    public void createSearchResultsViewModel() {
        searchResultsViewModel = new SearchResultsViewModel();
    }
    @Test
    public void isMore() {
        // ...then the result should be the expected one.
        searchResultsViewModel.totalpages=1;
        searchResultsViewModel.page = 2;
        assertThat(searchResultsViewModel.page, is(2));
        assertThat(searchResultsViewModel.totalpages, is(1));
        assertThat(searchResultsViewModel.isMore(), is(false));
    }

}