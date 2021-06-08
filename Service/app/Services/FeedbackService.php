<?php
namespace App\Services;

use App\Models\AssignmentRequest;
use Illuminate\Support\Facades\DB;
use App\Models\Feedback;

class FeedbackService {
    public function __construct() {

    }
    public function showFeedback() {
        $feedback = DB::table('Feedback')->select(

        'FeedbackID as feedbackId',
        'Title as title',
        'AdminID as adminId',
        'IsDeleted as isDeleted',
        'TypeFeedbackID as typeFeedbackID',
        )
        ->get();
        return json_encode($feedback);
    }
}