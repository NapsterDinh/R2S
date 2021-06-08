<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class Module extends Migration
{
     /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('Module', function (Blueprint $table) {
            $table->integer('ModuleID')->primary();
            $table->string('ModuleName')->nullable();
            $table->date('StartTime')->nullable();
            $table->string('EndTime')->nullable();
            $table->boolean('IsDeleted')->nullable();
            $table->string('AdminID')->nullable();            
            $table->integer('FeedbackID')->nullable();
            $table->dateTime('FeedbackStartTime')->nullable();
            $table->dateTime('FeedbackEndTime')->nullable();
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
        Schema::dropIfExists('Module');
    }
}
