<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class QuestionForeignKey extends Migration
{
     /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Question', function (Blueprint $table) {
            $table->foreign('TopicID')->references('TopicID')->on('Topic');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('Question', function (Blueprint $table) {
            $table->dropForeign(['TopicID']);
        });
    }
}
