<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Assignment extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Assignment', function (Blueprint $table) {
            $table->integer('ClassID');
            $table->integer('ModuleID');   
            $table->string('TrainerID');
            $table->string('RegistrationCode')->nullable();           
            $table->timestamps();

            $table->primary(['ClassID','ModuleID','TrainerID']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Assignment');
    }
}
