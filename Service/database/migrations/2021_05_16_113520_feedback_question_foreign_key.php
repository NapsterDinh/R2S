<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class FeedbackQuestionForeignKey extends Migration
{
     /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Feedback_Question', function (Blueprint $table) {
            $table->foreign('FeedbackID')->references('FeedbackID')->on('Feedback');
            $table->foreign('QuestionID')->references('QuestionID')->on('Question');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('Feedback_Question', function (Blueprint $table) {
            $table->dropForeign(['FeedbackID', 'QuestionID']);
        });
    }
}
