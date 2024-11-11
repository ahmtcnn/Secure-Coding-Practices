class GetReportView(APIView):
    @jwt_auth_required
    def get(self, request, user=None):
        report_id = request.GET.get("report_id", None)
        if not report_id:
            return Response(
                {"message": messages.REPORT_ID_MISSING},
                status=status.HTTP_400_BAD_REQUEST,
            )

        if not report_id.isnumeric():
            return Response(
                {"message": messages.INVALID_REPORT_ID},
                status=status.HTTP_400_BAD_REQUEST,
            )

        service_request = ServiceRequest.objects.filter(id=report_id).first()
        if not service_request:
            return Response(
                {"message": messages.REPORT_DOES_NOT_EXIST},
                status=status.HTTP_400_BAD_REQUEST,
            )

        # Authorization check embedded in the flow
        if not (user.id == service_request.owner_id or user.is_superuser):
            raise PermissionDenied("You do not have permission to access this service request.")

        serializer = ServiceRequestSerializer(service_request)
        response_data = dict(serializer.data)
        return Response(response_data, status=status.HTTP_200_OK)

