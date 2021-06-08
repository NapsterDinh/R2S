<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class EnrollmentForeignKey extends Migration
{
      /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Enrollment', function (Blueprint $table) {
            $table->foreign('ClassID')->references('ClassID')->on('Class');
            $table->foreign('TraineeID')->references('UserName')->on('Trainee');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('Enrollment', function (Blueprint $table) {
            $table->dropForeign(['ClassID', 'TraineeID']);
        });
    }
}
