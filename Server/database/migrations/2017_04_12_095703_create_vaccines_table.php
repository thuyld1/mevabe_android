<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;

class CreateVaccinesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('vaccines', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('v_code', false, true)->unique();
            $table->string('v_name', 100);
            $table->string('v_period', 100);
            $table->integer('v_period_f', 10);
            $table->integer('v_period_t', 10);
            $table->string('v_short_des', 255);
            $table->string('v_url', 255);
            $table->integer('status', 1)->default(0);
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
        Schema::drop('vaccines');
    }
}
