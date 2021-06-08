<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Answer extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Answer', function (Blueprint $table) {
            $table->integer('ClassID');
            $table->integer('ModuleID');                     
            $table->string('TraineeID'); 
            $table->integer('QuestionID');                                         
            $table->timestamps();

            $table->primary(['ClassID','ModuleID','TraineeID','QuestionID']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Answer');
    }
}
