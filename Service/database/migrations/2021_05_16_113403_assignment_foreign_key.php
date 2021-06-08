<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AssignmentForeignKey extends Migration
{
      /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('Assignment', function (Blueprint $table) {
            $table->foreign('ClassID')->references('ClassID')->on('Class');
            $table->foreign('ModuleID')->references('ModuleID')->on('Module');
            $table->foreign('TrainerID')->references('UserName')->on('Trainer');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('Assignment', function (Blueprint $table) {
            $table->dropForeign(['ClassID', 'ModuleID', 'TrainerID']);
        });
    }
}
