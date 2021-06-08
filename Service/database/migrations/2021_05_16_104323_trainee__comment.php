<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class TraineeComment extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Trainee_Comment', function (Blueprint $table) {
            $table->integer('ClassID');
            $table->integer('ModuleID');  
            $table->string('TraineeID');                       
            $table->string('Comment')->nullable();                                            
            $table->timestamps();

            $table->primary(['ClassID','ModuleID','TraineeID']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Trainee_Comment');
    }
}
