<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Classs extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Class', function (Blueprint $table) {
            $table->integer('ClassID')->primary();
            $table->string('ClassName')->nullable();   
            $table->integer('Capacity')->nullable();
            $table->date('StartTime')->nullable(); 
            $table->date('EndTime')->nullable();           
            $table->boolean('IsDeleted')->nullable();                     
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
        Schema::dropIfExists('Class');
    }
}
