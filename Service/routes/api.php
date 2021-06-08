<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

// Route::middleware('auth:api')->get('/user', function (Request $request) {
//     return $request->user();
// });

Route::prefix('trainee')->group(function () {
    Route::get('', 'App\Http\Controllers\Account\AccountController@showTrainee');
    Route::post('', 'App\Http\Controllers\Account\AccountController@insertTrainee');
    Route::get('/{username}/{password}', 'App\Http\Controllers\Account\AccountController@showUserTrainee');
});

Route::prefix('question')->group(function () {
    Route::get('', 'App\Http\Controllers\Question\QuestionController@showQuestion');
    Route::post('', 'App\Http\Controllers\Question\QuestionController@insertQuestion');
    Route::delete('', 'App\Http\Controllers\Question\QuestionController@deleteQuestion');
    Route::put('', 'App\Http\Controllers\Question\QuestionController@updateQuestion');
    Route::get('search', 'App\Http\Controllers\Question\QuestionController@searchQuestion');

});

Route::prefix('topic')->group(function () {
    Route::get('', 'App\Http\Controllers\Topic\TopicController@showTopic');
});
Route::prefix('assignment')->group(function () {
    Route::get('/{idUser}/{role}', 'App\Http\Controllers\AssignmentController@showAllAssignment');
    Route::get('/search/{idUser}/{role}/{str_search}', 'App\Http\Controllers\AssignmentController@searchAssignment');
    Route::post('/add', 'App\Http\Controllers\AssignmentController@addAssignment');
    Route::delete('/delete/{isFirstStep}', 'App\Http\Controllers\AssignmentController@deleteAssignment');
    Route::put('/edit', 'App\Http\Controllers\AssignmentController@updateAssignment');
});

Route::prefix('trainer')->group(function () {
    Route::get('', 'App\Http\Controllers\TrainerController@showAllTrainer');
    Route::get('/{username}/{password}', 'App\Http\Controllers\TrainerController@showUserTrainer');    
});

Route::prefix('admin')->group(function () {
    Route::get('', 'App\Http\Controllers\AdminController@showAdmin');
    Route::get('/{username}/{password}', 'App\Http\Controllers\AdminController@showUserAdmin');

});

Route::prefix('class')->group(function () {
    Route::get('/{isAll}', 'App\Http\Controllers\ClassController@showClass');
    Route::get('', 'App\Http\Controllers\ClassController@getAll');
    Route::post('', 'App\Http\Controllers\ClassController@insertClass');
    Route::put('', 'App\Http\Controllers\ClassController@editClass');
    Route::delete('', 'App\Http\Controllers\ClassController@deleteClass');
    
});

Route::prefix('module')->group(function () {
    Route::get('/{isAll}', 'App\Http\Controllers\ModuleController@showModule');
    Route::get('', 'App\Http\Controllers\ModuleController@getAll');
    Route::post('', 'App\Http\Controllers\ModuleController@insertModule');
    Route::put('', 'App\Http\Controllers\ModuleController@editModule');
    Route::delete('', 'App\Http\Controllers\ModuleController@deleteModule');
}); 

Route::prefix('feedback')->group(function () {
    Route::get('', 'App\Http\Controllers\FeedbackController@showFeedback');
});

Route::prefix('enrollment')->group(function () {
    Route::get('', 'App\Http\Controllers\EnrollmentController@getAll');
});

Route::prefix('statistic')->group(function () {
    Route::get('/{isAdmin}/{idUSer}/{idClass}/{idModule}', 'App\Http\Controllers\StatisticController@showOverview');
});

