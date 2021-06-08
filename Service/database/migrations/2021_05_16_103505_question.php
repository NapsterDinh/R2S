<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Question extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Question', function (Blueprint $table) {
            $table->integer('QuestionID');
            $table->integer('TopicID');    
            $table->string('QuestionContent')->nullable();                     
            $table->boolean('IsDeleted')->nullable();                                      
            $table->timestamps();

            $table->primary(['QuestionID','TopicID']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Question');
    }
}
