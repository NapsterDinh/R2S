<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Trainer extends Migration
{
     /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Trainer', function (Blueprint $table) {
            $table->string('UserName')->primary();
            $table->string('Email')->nullable();            
            $table->string('Phone')->nullable();
            $table->string('Address')->nullable();
            $table->string('Name')->nullable();
            $table->boolean('IsActive')->nullable();
            $table->string('Password')->nullable();
            $table->integer('IdSkill')->nullable();
            $table->string('ActivationCode')->nullable();
            $table->string('ResetPasswordCode')->nullable();
            $table->boolean('IsReceiveNotification')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('Trainer');
    }
}
