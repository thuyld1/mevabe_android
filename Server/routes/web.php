<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//Route::get('/', function () {
//    return view('welcome');
//});
Route::get('/', 'BackendController@index');

Auth::routes();

// For Backend Dashboard
Route::get('backend', 'BackendController@index');

// For medical news function
Route::resource('backend/medical-news', 'MedicalNewsController');

// For doctors function
Route::resource('backend/doctors', 'DoctorsController');
Route::resource('backend/setting-province', 'LocationProvinceController');
Route::resource('backend/setting-district', 'LocationDistrictController');
Route::resource('backend/doctor-specialization', 'DoctorSpecializationController');

// For vaccines function
Route::resource('backend/vaccines', 'VaccinesController');