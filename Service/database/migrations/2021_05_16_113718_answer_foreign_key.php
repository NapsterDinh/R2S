<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AnswerForeignKey extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Answer', function (Blueprint $table) {
            $table->foreign('TraineeID')->references('UserName')->on('Trainee');
            $table->foreign('ClassID')->references('ClassID')->on('Class');
            $table->foreign('QuestionID')->references('QuestionID')->on('Question');
            $table->foreign('ModuleID')->references('ModuleID')->on('Module');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('Answer', function (Blueprint $table) {
            $table->dropForeign(['TopicID','ClassID','QuestionID','ModuleID']);
        });
    }
}
