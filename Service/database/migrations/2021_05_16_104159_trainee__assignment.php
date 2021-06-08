<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class TraineeAssignment extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Trainee_Assignment', function (Blueprint $table) {
            $table->string('RegistrationCode');
            $table->string('TraineeID');                       
            $table->timestamps();

            $table->primary(['RegistrationCode','TraineeID']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Trainee_Assignment');
    }
}
