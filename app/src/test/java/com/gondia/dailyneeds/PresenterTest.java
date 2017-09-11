package com.gondia.dailyneeds;

import com.gondia.dailyneeds.Login.LoginActivityMVP;
import com.gondia.dailyneeds.Login.LoginActivityPresenter;
import com.gondia.dailyneeds.Login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Nilesh1 on 11-09-2017.
 */

public class PresenterTest {
    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter;
    User user;

    @Before
    public void setup(){
        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user=new User("Amish","Kundnani");

        when(mockLoginModel.getUser()).thenReturn(user);

        mockView =mock(LoginActivityMVP.View.class);
        presenter=new LoginActivityPresenter(mockLoginModel);
        presenter.setView(mockView);
    }

   /* @Test
    public void noInteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockView);
    }*/
   @Test
    public void whenUserEnteredValidUsername(){
       when(mockLoginModel.getUser()).thenReturn(user);

       presenter.getCurrentUser();
       verify(mockLoginModel,times(1)).getUser();
       verify(mockView,times(1)).setFirstName("Amish");
       verify(mockView,times(1)).setLastName("Kundnani");
   }
}
