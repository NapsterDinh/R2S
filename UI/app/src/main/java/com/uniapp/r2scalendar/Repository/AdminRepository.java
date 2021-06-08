package com.uniapp.r2scalendar.Repository;

import com.uniapp.r2scalendar.Model.Admin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdminRepository {
    @GET("admin")
    Call<List<Admin>> getAll();
}
